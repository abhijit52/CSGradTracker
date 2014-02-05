<%@ page import="com.shrike.model.services.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.sql.*"%>
<jsp:useBean id="photo" class="com.shrike.model.services.images"
	scope="session" />
<%
	int iNumPhoto;
	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;
	OutputStream o = null;
	byte[] imgData;

	if (request.getParameter("user") != null) {

		try {
			ConnectionDAO cn = new ConnectionDAO();
			conn = cn.getConnectionObject();

			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			rs = stmt
					.executeQuery("SELECT idUserDoc FROM UserDoc WHERE username = \""
							+ request.getParameter("user").trim()
							+ "\" AND DocType=\"jpg\"");

			if (!rs.next()) {
				rs = stmt
						.executeQuery("SELECT idUserDoc FROM UserDoc WHERE Description = \"No Image"
								+ "\" AND DocType=\"jpg\"");
				if (rs.next()) {
					iNumPhoto = rs.getInt(1);
					imgData = images.getPhoto(conn, iNumPhoto);
					// display the image
					response.setContentType("image/jpg");

					o = response.getOutputStream();
					o.write(imgData);
				} else {
					response.setContentType("image/jpg");
					o = response.getOutputStream();
					o.write(null);
				}
				o.flush();
				o.close();
			} else {
				iNumPhoto = rs.getInt(1);
				imgData = images.getPhoto(conn, iNumPhoto);
				// display the image
				response.setContentType("image/jpg");

				o = response.getOutputStream();
				o.write(imgData);
				o.flush();
				o.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}
	}
%>