package org.abondar.experimental.cameldemo.shoppingcart.util;

public class RouteUtil {

    public static final String ERROR_LOG = "log:org.abondar.experimental.cameldemo.shoppingcart.route?level=ERROR";

    public static final String INFO_LOG = "log:org.abondar.experimental.cameldemo.shoppingcart.route?level=INFO";
    public static final String POST_ENDPOINT =  "direct:post";

    public static final String GET_ID_ENDPOINT = "direct:getById";

    public static final String GET_LIMIT_ENDPOINT = "direct:getByLimit";

    public static final String GET_ITEMS_ENDPOINT = "direct:getItems";

    public static final String POST_ROUTE= "postRoute";

    public static final String GET_ID_ROUTE = "getByIdRoute";

    public static final String GET_LIMIT_ROUTE = "getByLimitRoute";

    public static final String GET_ITEM_ROUTE = "getItemsRoute";


    public static final String GET_ID_QUERY = "?orderBy=\"$key\"&equalTo=\"${header.id}\"";

    public static final String GET_LIMIT_QUERY = "?orderBy=\"$key\"&limitToFirst=${header.limit}";

    private RouteUtil(){}
}
