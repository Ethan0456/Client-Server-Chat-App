import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.concurrent.thread

class Channel (
    val name: String,
    val socket: Socket
){
    private var bufferReader: BufferedReader = BufferedReader(InputStreamReader(socket.getInputStream()))
    private var printWriter: PrintWriter = PrintWriter(socket.getOutputStream())
    private lateinit var textOfEditText: String
    private lateinit var gui: Graphical

    init {
        startReading()
        startWriting()
    }

    fun write(text: String){

    }

    fun read(){

    }

    fun startReading(){
        thread(start=true) {
            println("Reader Started...")
            try {
                while(!socket.isClosed){
                    val msgCli = bufferReader.readLine()
//                    if (msg.equals("#E")) {
//                        println("$name terminated the chat.")
//                        socket.close()
//                    }
                    printWriter.println(msgCli)
                    printWriter.flush()
                }
            }
            catch(e: Exception){
                e.printStackTrace()
            }
        }
    }

    fun startWriting(){
        thread(start=true) {
            println("Writer Started...")
            try {
                while(!socket.isClosed){
                }
            }
            catch(e: Exception){
                e.printStackTrace()
            }
        }
    }
}