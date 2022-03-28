package com.hmy.transport;

import com.hmy.gkrpc.Peer;
import com.hmy.gkrpc.ServiceDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlet.Source;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;


@Slf4j
public class HttpTransportServer implements TransportServer {
    private Server server;
    private RequestHandler handler;


    @Override
    public void init(int port, RequestHandler handler) {
        // server 启动一个Server 服务，监听port端口
        // handler 用来获取请求的 inputStream 和 outputStream
        this.server = new Server(port);
        this.handler = handler;

        // servlet 接受请求
        ServletContextHandler ctx = new ServletContextHandler();
        server.setHandler(ctx);
        // ServletHolder 网络请求抽象
        ServletHolder holder = new ServletHolder(new RequestServlet());
        ctx.addServlet(holder, "/*");
    }

    @Override
    public void start() {
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }

    @Override
    public void stop() {
        try {
            server.stop();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(), e);
        }
    }

    private class RequestServlet extends HttpServlet {
        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            log.info("get request success");
            ServletInputStream in = req.getInputStream();
            ServletOutputStream out = resp.getOutputStream();
            if (handler != null) {
                handler.onRequest(in, out);
            }
            out.flush();
        }
    }
}
