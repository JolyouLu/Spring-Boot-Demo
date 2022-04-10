package top.jolyoulu.apiVersion;

/**
 * api版本字符串转换工具类
 */
public class ApiConverter {

    // 将 string 格式的版本号转换为 ApiItem 的转换类，并且支持了默认版本为1.0.0的设定
    public static ApiItem convert(String api) {
        ApiItem apiItem = new ApiItem();
        if (api == null || "".equals(api.trim())) {
            //若未标识版本号，返回默认的版本类
            return apiItem;
        }
        api = api.trim();
        String[] cells = api.split("\\.");
        apiItem.setHigh(Integer.parseInt(cells[0]));
        if (cells.length > 1) {
            apiItem.setMid(Integer.parseInt(cells[1]));
        }

        if (cells.length > 2) {
            apiItem.setLow(Integer.parseInt(cells[2]));
        }
        return apiItem;
    }

}
