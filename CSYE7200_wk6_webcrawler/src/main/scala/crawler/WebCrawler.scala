package crawler

import java.net.URL

import depfun.MonadOps

import scala.io.Source
import scala.util._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent._
import scala.concurrent.duration._
import scala.xml.Node

/**
  * Created by Shuxian on 10/31/16.
  */
object WebCrawler extends App {

  def getURLContent(u: URL): Future[String] = {
    for {
      source <- Future(Source.fromURL(u))
    } yield source mkString
  }

  def wget(u: URL): Future[Seq[URL]] = {
    // TODO implement. 15 points. Hint: write as a for-comprehension, using the constructor new URL(URL,String) to get the appropriate URL for relative links
    def getURLs(ns: Node): Seq[URL] = for{
      n<-ns\\"a"
      url = n \@ "href"
//      url = n \ "@href"
//      nh<-url.apply(0)
//      }yield new URL(u,nh.toString)
    } yield new URL(u,url)
//    {
//      val a = ns \\ "a"
//
//      val b = a map( _ \@ "href")
//
//      for{
//         url <- b
//      }yield new URL(u,url)
//    }
    def getLinks(g: String): Try[Seq[URL]] =
      for (n <- HTMLParser.parse(g) recoverWith({case f=>Failure(new RuntimeException(s"parse problem with URL $u: $f"))}))
        yield getURLs(n)
    // TODO implement. 8 points. Hint: write as a for-comprehension, using getURLContent (above) and getLinks above. You might also need MonadOps.future
    for(l<-getURLContent(u);fsu<-MonadOps.future(getLinks(l))) yield fsu
  }

  def wget(us: Seq[URL]): Future[Seq[Either[Throwable,Seq[URL]]]] = {
    val us2 = us.distinct take 10
    // TODO implement the rest of this, based on us2 instead of us. 12 points.
    // Hint: Use wget(URL) (above). MonadOps.sequence and Future.sequence are also available to you to use.

//    val sfe=for(fsu<-(for(u<-us2) yield wget(u))) yield MonadOps.sequence(fsu)
//    Future.sequence(sfe)

    val sfsu=for(u<-us2) yield wget(u)
    val sfe=MonadOps.sequence(sfsu)
    Future.sequence(sfe)

  }

  def crawler(depth: Int, args: Seq[URL]): Future[Seq[URL]] = {
    def inner(urls: Seq[URL], depth: Int, accum: Seq[URL]): Future[Seq[URL]] =
      if ( depth>0 )
        for (us <- MonadOps.flattenRecover(wget(urls),{x => System.err.println(x)}); r <- inner(us,depth-1,accum ++: urls)) yield r
      else
        Future.successful(accum)
    inner(args, depth, List())
  }
}
