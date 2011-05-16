/** ------------------------------------------------------------
 * ListItemsController.java
 *
 * Big Cyber City
 *
 * @author wbruschi [ Dec 11, 2008 ]
 * ------------------------------------------------------------
 */
package com.bigcybercity.presentation;

import java.util.List;

import org.springframework.web.servlet.mvc.AbstractCommandController;

import com.bigcybercity.util.ListItem;

/** 
 * A general controller for presenting lists to the user, such
 * as friends, comments, etc
 */

public abstract class ListItemsController extends AbstractCommandController {
	public ListItemsController() {
		setCommandClass(Id.class);
		setCommandName("id");
	}
	
	public String makeList(List<ListItem> results, int currentPage, int numOfResults) {
		StringBuilder html = new StringBuilder("");
		StringBuilder pageBox = pageBox(currentPage, numOfResults);
		html.append(pageBox);
		html.append(listResults(results));
		html.append(pageBox);
		return html.toString();

	}
	

	public StringBuilder listResults(List<ListItem> results) {

			StringBuilder html = new StringBuilder("");
			for (ListItem user : results) {
				html.append("<table><tr><td valign=top>");
				html.append("<div class=picture>");
				html.append("<a href=/profile.htm?id=");
				html.append(user.getUserId());
				html.append("><img src=");
				html.append(user.getPhoto());
				html.append(" ></a></div>");
				html.append("</td><td valign=top width=50%>");
				html.append("<div class=stats><div class=name>");
				html.append("<a href=/profile.htm?id=");
				html.append(user.getUserId()).append(" >");
				html.append(user.getUserName());
				html.append("</a></div><div class=info>");
				html.append(user.getInfo());
				html.append("</div><div class=contents> ");
				html.append(user.getContents());
				if (user.isCanDelete()) {
					html.append("<br /><br /><a href=# onclick=deleteItem(");
					html.append(user.getId()).append(") >Delete</a>");
				}
				html.append("</div></div></td></tr></table>");
			}
			return html;

		}


	public StringBuilder pageBox(int currentPage, int numOfResults) {


			if (currentPage == 0)
				currentPage = 1;
			int numOfPages = (numOfResults > 10) ? ((numOfResults - 1) / 10) + 1
					: 1;
			if (currentPage > numOfPages)
				currentPage = numOfPages;
			int startPage = (currentPage <= 15) ? 1 : currentPage - 15;
			int endPage = (startPage + 30 > numOfPages) ? numOfPages
					: startPage + 30;

			StringBuilder pageBox = new StringBuilder("");

			if (numOfPages > -1) {
				pageBox.append("<div class=pageBox>");
				pageBox.append(numOfResults);
				pageBox.append(" Results - Go to Page: <br />");
				if (currentPage > 1) {
					pageBox
							.append("<span class=arrows><a href=# onclick=gotoPage(");
					pageBox.append(currentPage - 1);
					pageBox.append(")> &lt;&lt;</a></span>");
				}
				// Print the page numbers before the current page, 10 max
				for (int i = startPage; i < currentPage; i++) {
					pageBox.append("<a href=# onclick=gotoPage(");
					pageBox.append(i);
					pageBox.append(")> ");
					pageBox.append(i);
					pageBox.append(" </a> ");
				}

				// Print the current page
				pageBox.append("<span class=currentPage> ");
				pageBox.append(currentPage);
				pageBox.append(" </span>");

				// Print the page numbers after the current page
				for (int i = currentPage + 1; i <= endPage; i++) {
					pageBox.append("<a href=# onclick=gotoPage(");
					pageBox.append(i);
					pageBox.append(")> ");
					pageBox.append(i);
					pageBox.append(" </a> ");
				}
				if (currentPage < endPage) {
					pageBox
							.append("<span class=arrows><a href=# onclick=gotoPage(");
					pageBox.append(currentPage + 1);
					pageBox.append(")> &gt;&gt;</a></span>");
				}
				pageBox.append("</div>");
			}
			return pageBox;
		}

	 

}


class Id {
	private long id;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}




