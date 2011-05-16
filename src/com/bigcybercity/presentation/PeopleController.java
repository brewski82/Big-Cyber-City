/** ------------------------------------------------------------
 * PeopleController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 6, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import com.bigcybercity.service.SqlService;
import com.bigcybercity.service.UserService;
import com.bigcybercity.util.ListItem;
import com.bigcybercity.util.SearchInput;

/**
 * The Big Cyber City people search page
 */
public class PeopleController extends ListItemsController {
	/**
	 * Injected by Spring
	 */
	private SqlService sqlService;
	private UserService userService;

	public void setSqlService(SqlService sqlService) {
		this.sqlService = sqlService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	protected ModelAndView handle(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, BindException arg3)
			throws Exception {
		return new ModelAndView("people");
	}


	/**
	 * Serves the AJAX request for a people search
	 * 
	 * @param input
	 * @return the search results in html
	 */
	public String search(SearchInput input) {

		// Construct the filter for the search
		List<Criterion> crit = new ArrayList<Criterion>();

		if (!(input.getGender().equals("Gender")))
			crit.add(Restrictions.eq("gender", input.getGender()));

		if (input.getStartAge() >= 18) {
			Calendar startDate = Calendar.getInstance();
			startDate.set(Calendar.YEAR, Calendar.getInstance().get(
					Calendar.YEAR)
					- input.getStartAge());
			crit.add(Expression.le("birthdate", startDate));
		}

		if (input.getEndAge() >= 18) {
			Calendar endDate = Calendar.getInstance();
			endDate.set(Calendar.YEAR, Calendar.getInstance()
					.get(Calendar.YEAR)
					- input.getEndAge() - 1);
			crit.add(Expression.ge("birthdate", endDate));
		}

		if (!(input.getStatus().equals("Status")))
			crit.add(Restrictions.eq("relationshipStatus", input.getStatus()));

		if (input.getMiles() >= 0 && input.getMiles() <= 500
				&& input.getZip().matches("\\d\\d\\d\\d\\d")) {
			crit.add(Restrictions.in("zip", sqlService.getNearbyZips(input
					.getZip(), input.getMiles())));
		}

		// Construct list of people from the search
		List<ListItem> results = userService
				.search(crit, input.getPageNumber());
		
		return makeList(results, input.getPageNumber(),
				userService.numOfSearchResults(crit));

	}


}

