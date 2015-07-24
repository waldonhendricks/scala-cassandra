import com.datastax.driver.core._
import scala.collection.JavaConversions._

object ConnectionPlayground extends App {

  // http://docs.datastax.com/en/developer/java-driver/2.1/java-driver/quick_start/qsSimpleClientCreate_t.html

  val session = connect("127.0.0.1")

  private def connect(node: String)  =  {
    val cluster = Cluster.builder().addContactPoint(node).build()
    val metadata = cluster.getMetadata
    println(s"Connected to cluster ${metadata.getClusterName}")

    metadata.getAllHosts.toSeq foreach { host =>
      printf("Datacenter: %s; Host: %s; Rack: %s",
        host.getDatacenter, host.getAddress, host.getRack)
    }

    cluster
  }

}
