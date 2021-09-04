/**
 */
/**/
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.webapp.Configuration.ClassList;
import org.eclipse.jetty.webapp.WebAppContext;
//Controller for the server
public class Controller {

	public static void main(String[] args) throws Exception {
		//Creates new server object on port 8005
		Server server = new Server(8005);
		//Creates the web app css and its info
		WebAppContext ctx = new WebAppContext(); 
		//sets resoruce to be the webapp folder
		ctx.setResourceBase("webapp"); 
		//sets the path to begin /database/api
		ctx.setContextPath("/database"); 
		//Sets the jetty server webapp
		ctx.setAttribute("org.eclipse.jetty.server.webapp.ContainerIncludeJarPattern", ".*/[^/]*jstl.*\\.jar$");
		//sets server default to the server object
		ClassList classList = ClassList.setServerDefault(server);
		//creates jetty imports
		classList.addBefore("org.eclipse.jetty.webapp.JettyWebXmlConfiguration", "org.eclipse.jetty.annotations.AnnotationConfiguration");
	
		//Adds a servlet for the api with path /api
		ctx.addServlet("servlets.ApiServlet", "/api");
		//sets the handler to be all the thing starting with ctx.
		server.setHandler(ctx);
		//starts the server
		server.start();
		//Joins server
		server.join();
	}	
}
