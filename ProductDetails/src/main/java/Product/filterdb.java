package Product;

import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
@WebServlet("/Get")

public class filterdb extends HttpServlet
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        response.setContentType ("text/html");
        PrintWriter out = response.getWriter ();
        String empid = request.getParameter ("id");
        try
        {
            Class.forName ("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection ("jdbc:mysql://localhost:3307/ecommerce1", "root", "root");
            PreparedStatement ps =
         con.prepareStatement ("select * from eproduct where id=?");
            ps.setString (1, empid);
            out.print ("<table width=50% border=1>");
            out.print ("<caption>Product Details:</caption>");
            ResultSet rs = ps.executeQuery ();
            /* Printing column names */
            out.print ("</br></br>");
            ResultSetMetaData rsmd = rs.getMetaData ();
            int total = rsmd.getColumnCount ();
            out.print ("<tr>");
            for (int i = 1; i <= total; i++)
         {
             out.print ("<th>" + rsmd.getColumnName (i) + "</th>");
         }
            out.print ("</tr>");
            /* Printing result */
            while (rs.next ())
         {
            // out.print ("<tr><td>" + rs.getString (1) + "</td><td>" +  rs.getString (2) + " </td><td>" + rs.getInt (3) + "</td></tr>");
            	
            	out.print("<tr><td>'"+rs.getInt(1)+"'</td><td><b>'"+rs.getString(2)+"'</b></td><td>'"+rs.getBigDecimal(3)+"'</td><td>'"+rs.getTimestamp(4)+"'</td>");
         }
            out.print ("</table>");
        }
        catch (Exception e2)
        {
            e2.printStackTrace ();
        }
        finally
        {
            out.close ();
        }
    }
    @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}