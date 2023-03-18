package com.example.learn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootExceptionReporter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Collection;


//@ComponentScan("com.example.learn.*")
@EnableScheduling
@MapperScan("com.example.learn.gp.dao")
@PropertySource(value = { "classpath:config/path.properties",
		"classpath:config/syncdate.properties" }, ignoreResourceNotFound = true,
		encoding = "utf-8")
@SpringBootApplication
public class LearnApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnApplication.class, args);
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		ConfigurableApplicationContext context = null;
		Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();

		stopWatch.stop();

		System.out.println(stopWatch.getTotalTimeNanos());
		System.out.println(stopWatch.getLastTaskTimeMillis());
	}

//	@Bean
//    public InitializingAdvice getResponseBodyWrap() {
//        return new InitializingAdvice();
//    }


		// 创建StopWatch对象，用于统计run方法启动时长。     StopWatch stopWatch = new StopWatch();
		// 启动统计     stopWatch.start();     ConfigurableApplicationContext context = null;
		// Collection<SpringBootExceptionReporter> exceptionReporters = new ArrayList<>();
		// 配置Headless属性     configureHeadlessProperty();
		// 获得SpringApplicationRunListener数组，
		// 该数组封装于SpringApplicationRunListeners对象的listeners中。
		// SpringApplicationRunListeners listeners = getRunListeners(args);
		// 启动监听，遍历SpringApplicationRunListener数组每个元素，并执行。
		// listeners.starting();
		// try {         // 创建ApplicationArguments对象
		// ApplicationArguments applicationArguments =
		// new DefaultApplicationArguments(args);
		// 加载属性配置，包括所有的配置属性。
		// ConfigurableEnvironment environment =
		// prepareEnvironment(listeners, applicationArguments);
		// configureIgnoreBeanInfo(environment);
		// 打印Banner         Banner printedBanner = printBanner(environment);
		// 创建容器         context = createApplicationContext();
		// 异常报告器         exceptionReporters =
		// getSpringFactoriesInstances(             SpringBootExceptionReporter.class,
		// new Class[] { ConfigurableApplicationContext.class }, context);
		// 准备容器，组件对象之间进行关联。         prepareContext(context, environment,
		// listeners, applicationArguments, printedBanner);
		// 初始化容器         refreshContext(context);
		// 初始化操作之后执行，默认实现为空。         afterRefresh(context, applicationArguments);
		// 停止时长统计         stopWatch.stop();
		// 打印启动日志         if (this.logStartupInfo) {
		// new StartupInfoLogger(this.mainApplicationClass)
		// .logStarted(getApplicationLog(), stopWatch);         }
		// 通知监听器：容器完成启动。         listeners.started(context);
		// 调用ApplicationRunner和CommandLineRunner的运行方法。
		// callRunners(context, applicationArguments);     } catch (Throwable ex) {
		// 异常处理         handleRunFailure(context, ex, exceptionReporters, listeners);
		// throw new IllegalStateException(ex);     }
		// try {         // 通知监听器：容器正在运行。         listeners.running(context);     }
		// catch (Throwable ex) {         // 异常处理
		// handleRunFailure(context, ex, exceptionReporters, null);
		// throw new IllegalStateException(ex);     }     return context; }

}