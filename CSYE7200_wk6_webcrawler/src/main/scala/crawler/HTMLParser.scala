package crawler

import scala.util.Try

/**
  * Created by Shuxian on 10/31/16.
  */
object HTMLParser {
  import scala.xml.Node
  import scala.xml.parsing.NoBindingFactoryAdapter

  import org.xml.sax.InputSource
  import java.io.ByteArrayInputStream

  import org.ccil.cowan.tagsoup.jaxp.SAXFactoryImpl

  lazy val adapter = new NoBindingFactoryAdapter()
  lazy val parser = (new SAXFactoryImpl).newSAXParser

  def parse(html: String, encoding: String = "UTF-8"): Try[Node] = Try(this.parse(html.getBytes(encoding)))

  def parse(html: Array[Byte]): Node = {
    val stream = new ByteArrayInputStream(html)
    val source = new InputSource(stream)
    adapter.loadXML(source, parser)
  }
}
