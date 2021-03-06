package csdn;

import com.github.vector4wang.VWCrawler;
import com.github.vector4wang.service.CrawlerService;
import org.jsoup.nodes.Document;

/**
 * Created with IDEA
 * User: vector 
 * Data: 2018/7/10 0010
 * Time: 17:50
 * Description: csdn列表页的抓取示例
 */
public class TestMain {
	public static void main(String[] args) {
		new VWCrawler.Builder()
				// 配置参数
				.setHeader("User-Agent",
						"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.108 Safari/537.36") // 设置请求头
				.setUrl("https://blog.csdn.net/qqhjqs") // 设置爬虫起始地址
				.setThreadCount(20) // 设置几个线程抓取数据
				.setTimeOut(5000) // 设置超时时间

				// 抽象正则
				.setTargetUrlRex("https://blog.csdn.net/qqhjqs/article/details/[0-9]+") // 设置目标页面url的正则表达式

				// 解析页面
				.setPageParser(new CrawlerService<Blog>() {

					/**
					 * 目标页面的doc对象，还有通过注解处理后的对象
					 * @param doc 文档内容
					 * @param pageObj 封装的对象
					 */
					@Override
					public void parsePage(Document doc, Blog pageObj) {
						// 可进行二次处理
						pageObj.setReadNum(pageObj.getReadNum().replace("阅读数：", ""));
					}


					// 保存数据

					/**
					 * 可以做保存对象的处理
					 * @param pageObj 页面对象
					 */
					@Override
					public void save(Blog pageObj) {
						System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>: " + pageObj.toString());
					}
				}) // 自定义解析service


				.build().start(); // 启动

	}
}
