package top.jolyoulu.apiVersion;

import org.springframework.web.servlet.mvc.condition.RequestCondition;

import javax.servlet.http.HttpServletRequest;

/**
 * RequestCondition 请求映射条件的规则。
 * 一个 url，支持多个请求接口，可以考虑通过RequestCondition来实现,下面是具体的实现类
 * RequestCondition是Spring MVC对一个请求匹配条件的概念建模。
 *         最终的实现类可能是针对以下情况之一：路径匹配，头部匹配，请求参数匹配，
 *         可产生MIME匹配，可消费MIME匹配，请求方法匹配，或者是以上各种情况的匹配条件的一个组合
 * 这里采用头部匹配：即请求头匹配
 */
public class ApiCondition implements RequestCondition<ApiCondition> {

    private ApiItem version;

    public ApiCondition(ApiItem version) {
        this.version = version;
    }

    /**
     * 将此条件与另一个条件(如来自
     * *类型级和方法级{@code @RequestMapping}注释。
     * * @param其他条件组合。
     * * @return一个请求条件实例，它是组合的结果
     * *两个条件实例。
     */
    @Override
    public ApiCondition combine(ApiCondition other) {
        // 选择版本最大的接口
        return version.compareTo(other.version) >= 0 ? new ApiCondition(version) : new ApiCondition(other.version);
    }

    /**
     * 获取符合条件的实例
     * 检查条件是否与可能返回新内容的请求匹配
     * 为当前请求创建的实例。例如一个条件
     * *多个URL模式可能只返回带有这些模式的新实例
     * *匹配请求。
     * 对于CORS的跨域请求，条件应该匹配潜在的，
     * *实际请求(例如URL模式、查询参数和HTTP方法
     * *从“Access-Control-Request-Method”头)。如果一个条件不能
     * *匹配一个飞行前的请求，它应该返回一个实例
     * *空内容，因此不会导致匹配失败。
     * 如果匹配，返回一个条件实例，否则返回{@code null}。
     */
    // 方法中，控制了只有版本小于等于请求参数中的版本的 ApiCondition 才满足规则
    @Override
    public ApiCondition getMatchingCondition(HttpServletRequest request) {
        String version = request.getHeader("app-version");
        ApiItem item = ApiConverter.convert(version);
        // 获取所有小于等于版本的接口
        if (item.compareTo(this.version) >= 0) {
            return this;
        }
        return null;
    }

    // compareTo 指定了当有多个ApiCoondition满足这个请求时，选择最大的版本

    /**
     * 将这种情况与上下文中的另一种情况进行比较
     * 一个特定的要求。此方法假设两个实例都有
     * *通过{@link #getMatchingCondition(HttpServletRequest)}获得
     * *以确保他们只有当前请求相关的内容。
     */
    @Override
    public int compareTo(ApiCondition other, HttpServletRequest request) {
        // 获取最大版本对应的接口
        return other.version.compareTo(this.version);
    }


}
