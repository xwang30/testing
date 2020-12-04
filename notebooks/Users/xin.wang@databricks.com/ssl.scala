// Databricks notebook source
// MAGIC %sh
// MAGIC sudo apt-get install -y nmap

// COMMAND ----------

// MAGIC %sh
// MAGIC nmap --script ssl-enum-ciphers -p 443 slack.com

// COMMAND ----------

import java.util.Map;
import java.util.TreeMap;
import javax.net.ssl.SSLServerSocketFactory
import javax.net.ssl._
SSLContext.getDefault.getDefaultSSLParameters.getProtocols.foreach(println)
SSLContext.getDefault.getDefaultSSLParameters.getCipherSuites.foreach(println)

// COMMAND ----------

import org.asynchttpclient._
import org.asynchttpclient.Dsl._

val plainAsyncHttp = asyncHttpClient().prepareGet("https://slack.com/api/conversations.list").execute().get()

// COMMAND ----------

import java.net.URL
import scala.io.Source

  val url    = new URL("https://slack.com")
  val result = Source.fromInputStream(url.openStream()).mkString
  println(result)
  assert(result.contains("TLS 1.2"))

// COMMAND ----------

import javax.net.ssl._
import org.asynchttpclient.netty.ssl._
import org.asynchttpclient._
import org.asynchttpclient.Dsl._
val sslcontext12 = SSLContext.getInstance("TLSv1.2")
sslcontext12.init(null, null, null)
object CipherFactory extends JsseSslEngineFactory(sslcontext12) with SslEngineFactory {
  override def newSslEngine(config: _root_.org.asynchttpclient.AsyncHttpClientConfig, peerHost: _root_.java.lang.String, peerPort: Int): _root_.javax.net.ssl.SSLEngine = {
    val e = super.newSslEngine(config, peerHost, peerPort)
    e.setEnabledCipherSuites(Array("TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA", "TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA", "TLS_RSA_WITH_AES_128_CBC_SHA", "TLS_RSA_WITH_AES_256_CBC_SHA"))
    e
  }
}
val async12CipherClient = asyncHttpClient(new DefaultAsyncHttpClientConfig.Builder()
  .setSslEngineFactory(CipherFactory)
  .build()
)
val cipherCheck = async12CipherClient.prepareGet("https://www.howsmyssl.com/a/check").execute().get()
println(cipherCheck.getResponseBody)
val cipherSlack = async12CipherClient.prepareGet("https://slack.com/api/conversations.list").execute().get()
println(cipherSlack.getResponseBody)

// COMMAND ----------

import javax.net.ssl._
import org.asynchttpclient.netty.ssl._
import org.asynchttpclient._
import org.asynchttpclient.Dsl._
val sslcontext = SSLContext.getInstance("TLSv1.2")
sslcontext.init(null, null, null)

asyncHttpClient(new DefaultAsyncHttpClientConfig.Builder()
                .setSslEngineFactory(new JsseSslEngineFactory(sslcontext))
                .build()
               ).prepareGet("https://slack.com/api/conversations.list").execute().get()

// COMMAND ----------

import java.net.URL
import javax.net.ssl.HttpsURLConnection
import scala.io.Source
val url = new URL("https://slack.com/api/ist")
val httpsConnection = url.openConnection().asInstanceOf[HttpsURLConnection]
print(httpsConnection.connect())

// COMMAND ----------

import org.asynchttpclient._
import org.asynchttpclient.Dsl._

val plainAsyncHttp = asyncHttpClient().prepareGet("https://slack.com").execute().get()


// COMMAND ----------

val plainAsyncHttp = asyncHttpClient().prepareGet("https://slack.com/api/conversations.list").execute().get()


// COMMAND ----------

import java.util.Map;
import java.util.TreeMap;
import javax.net.ssl.SSLServerSocketFactory
import javax.net.ssl._
SSLContext.getDefault.getDefaultSSLParameters.getProtocols.foreach(println)
SSLContext.getDefault.getDefaultSSLParameters.getCipherSuites.foreach(println)

// COMMAND ----------

// MAGIC %sh
// MAGIC javac

// COMMAND ----------

val url    = new java.net.URL("https://slack.com")
val result = scala.io.Source.fromInputStream(url.openStream()).mkString
println(result)

// COMMAND ----------

// MAGIC %sh
// MAGIC cp /databricks/spark/dbconf/java/extra.security /databricks/spark/dbconf/java/extra_v1.security;
// MAGIC sed -i 's/, GCM//g' /databricks/spark/dbconf/java/extra_v1.security

// COMMAND ----------

// MAGIC %sh cat /databricks/spark/dbconf/java/extra_v1.security

// COMMAND ----------

// MAGIC %python
// MAGIC import ssl, sys
// MAGIC ssl.get_default_verify_paths()

// COMMAND ----------

// MAGIC %sh
// MAGIC ntpq -

// COMMAND ----------

// MAGIC %sh
// MAGIC cat /etc/ntp.conf

// COMMAND ----------

// MAGIC %scala
// MAGIC dbutils.fs.put("/xin/modify_cipher.sh", """#!/bin/bash
// MAGIC cp /databricks/spark/dbconf/java/extra.security /databricks/spark/dbconf/java/extra_v1.security;
// MAGIC sed -i 's/, GCM//g' /databricks/spark/dbconf/java/extra_v1.security
// MAGIC """, true)

// COMMAND ----------

// MAGIC %fs ls /

// COMMAND ----------

