import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import java.util.Scanner
import kotlin.concurrent.thread
class Client(val IP: String?, val port: String?, val name: String?) {
    private lateinit var socket: Socket
    init {
        try {
            println("Sending Request to server...")
            socket = Socket(IP,port!!.toInt())
            println("Connection Established")
            Graphical(name, socket)
        }
        catch(e: Exception) {
            e.printStackTrace()
        }
    }
}