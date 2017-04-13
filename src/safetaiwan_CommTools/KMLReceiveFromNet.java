package safetaiwan_CommTools;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;

public class KMLReceiveFromNet {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public KMLReceiveFromNet() {

	}

	public void downloadKML(String url, String outFilePath) {

		if (url.equals("") || url == null) {
			url = "http://gic.wra.gov.tw/gic/API/Google/DownLoad.aspx?fname=GWREGION";
		}

		String a = null;
		try {
			a = formatXML(url);
//			 System.out.println(a);
			List<String> lines = Arrays.asList(a);
			String s = CommonTools.APPLocation();
			String outFilePathALL = "";
			if (outFilePath.equals("") || outFilePath == null) {
				outFilePathALL = s + "/resources/kml/underwater.kml";
			} else {
				outFilePathALL = s + "/resources/kml/" + outFilePath;
			}
			Path file = Paths.get(outFilePathALL);
			Files.write(file, lines, Charset.forName("UTF-8"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static String formatXML(String xmlURL) {

		try {
			// String uri =
			// "http://api.flurry.com/eventMetrics/Event?apiAccessCode=YHJBA13CSKTMS6XHTM6M&apiKey=6XQY729FDU1CR9FKXVZP&startDate=2011-2-28&endDate=2011-3-1&eventName=Tip%20Calculated";

			// URL url = new URL(xmlURL);
			CommonTools commonTools = new CommonTools();
			HttpURLConnection connection = commonTools.SSLHttpConnection(xmlURL);
			connection.setRequestMethod("GET");
			// connection.setRequestProperty("Accept", "application/xml");

			InputStream src1 = connection.getInputStream();
			
			//add replace xmltitle 20170412
//			 String str = IOUtils.toString( src1 );
//			 IOUtils.closeQuietly( src1 );
//			 str = StringUtils.replace( str, "xmlns:atom=\"http://www.w3.org/2005/Atom\"", "xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:atom=\"http://www.w3.org/2005/Atom\"" );
//			 str = StringUtils.replace( str, "<kml", "<kml xmlns=\"http://www.opengis.net/kml/2.2\" " );
//			 System.out.println(str);
//			 ByteArrayInputStream bais = new ByteArrayInputStream( str.getBytes( "UTF-8" ) );
			 
			// final InputSource src = new InputSource(new StringReader(xml));
			final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src1)
					.getDocumentElement(); //modify 20170412 src1->bais
			final Boolean keepDeclaration = Boolean.valueOf(xmlURL.startsWith("<?xml"));

			// May need this:
			 System.setProperty(DOMImplementationRegistry.PROPERTY,"com.sun.org.apache.xerces.internal.dom.DOMImplementationSourceImpl");

			final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();
			// Set this to true if the output needs to be beautified.
			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
			// Set this to true if the declaration is needed to be outputted.
			writer.getDomConfig().setParameter("xml-declaration", keepDeclaration);

			return writer.writeToString(document);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
