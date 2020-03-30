package com.info.brochureatmobile.webservice;

import com.info.brochureatmobile.Model.AboutUsModel;
import com.info.brochureatmobile.Model.CategoryModel;
import com.info.brochureatmobile.Model.ClientsModel;
import com.info.brochureatmobile.Model.HomeModel;
import com.info.brochureatmobile.Model.NewsModel;
import com.info.brochureatmobile.Model.ProductModel;
import com.info.brochureatmobile.Model.RepresentativeModel;
import com.info.brochureatmobile.Model.ServiceModel;
import com.info.brochureatmobile.Model.CertificationModel;
import com.info.brochureatmobile.Model.SubscriptionModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

   /* @FormUrlEncoded
    @POST("pos/login")
    Call<LoginPojo> getLogin(@Field("email") String email,
                             @Field("password") String password,
                             @Field("device_id") String device_id);

    @FormUrlEncoded
    @POST("pos/checkpin")
    Call<LoginPojo> getPin(@Field("pos_id") String pos_id,
                           @Field("pos_pin") String pos_pin);*/

    @GET("apphomesetting")
    Call<HomeModel> getHomeData();

    @GET("getCategorybyproducts")
    Call<CategoryModel> getCategoryData();

    @GET("servicedata")
    Call<ServiceModel> getServiceData();

    @GET("certificationdata")
    Call<CertificationModel> getCertificationData();

    @GET("ourclientsdata")
    Call<ClientsModel> getOurClientsData();

    @GET("ourrepresentativedata")
    Call<RepresentativeModel> getRepresentativeData();

    @FormUrlEncoded
    @POST("cmsdata")
    Call<AboutUsModel> getAboutUs(@Field("cms_id") String x);


    @FormUrlEncoded
    @POST("getproductslist")
    Call<ProductModel> getProductList(@Field("category_id") String x,
                                      @Field("sub_category_id") String y);

    @FormUrlEncoded
    @POST("appsubscriber")
    Call<SubscriptionModel> getSubscription(@Field("name") String name,
                                            @Field("email_id") String mail,
                                            @Field("mobile_no") String phone,
                                            @Field("compnay_name") String company,
                                            @Field("page_type") String pagetyp,
                                            @Field("category_id") String category_id,
                                            @Field("product_id") String product_id);

    @GET("newsandeventsdata")
    Call<NewsModel> getNewsData();

    /*//@GET("product")
    @GET("product/products")
    Call<ArrayList<PojoCategoryItems>> getAllItems();

    @GET("customer/customers")
    Call<CustomersModel> getAllCustomers();


    @GET("coupons/coupons")
    Call<PojoCoupon> getAllCoupons();


    @GET("orders/orders")
    Call<ArrayList<OrderHistoryModel>> getOrderHistory();


    @FormUrlEncoded
    @POST("customer/loginuser")
    Call<NewLoginModel> checkLoginData(@Field("username") String username,
                                       @Field("password") String password);


    @FormUrlEncoded
    @POST("customers/addcustomers")
    Call<CustomersModel> addNewCustomer(@Field("email") String email,
                                        @Field("first_name") String first_name,
                                        @Field("last_name") String last_name,
                                        @Field("mobile_no") String mobile_no);


    @FormUrlEncoded
    @POST("get_orders.php?type=add")
    Call<TestData> getOrderResponse(@Field("item_contents") String cartItem,
                                    @Field("customer_id") String customer,
                                    @Field("pos_login_id") int pos_login_id,
                                    @Field("coupon_id") int couponId);

    @FormUrlEncoded
    @POST("get_orders.php?type=add")
    Call<TestData> getOrderResponse(@Field("item_contents") String cartItem,
                                    @Field("customer_id") String customer,
                                    @Field("pos_login_id") int pos_login_id);


    @GET("pos/getcategoryitem/{restaurant_id}/{category_id}/{timezone}")
    Call<PojoCategoryItems> getcategoryitem(@Path("restaurant_id") String restaurant_id,
                                            @Path("category_id") String category_id,
                                            @Path("timezone") String timezone);

    @GET("pos/getitemdetails/{item_id}")
    Call<ItemDetailsData> getItemDetails(@Path("item_id") String item_id);

    @FormUrlEncoded
    @POST("pos/checkcoupon")
    Call<PojoApplyCoupon> applyCoupon(@Field("coupon_code") String coupon_code,
                                      @Field("restaurant_id") String restaurant_id,
                                      @Field("total_amount") String total_amount,
                                      @Field("pos_id") String pos_id);

    @FormUrlEncoded
    @POST("pos/addcustomer")
    Call<AddCustomerResposne> addCustomer(@Field("customer_name") String customer_name,
                                          @Field("customer_email") String customer_email,
                                          @Field("customer_phone") String customer_phone,
                                          @Field("customer_resturant_id") String customer_resturant_id,
                                          @Field("customer_pos_id") String customer_pos_id);

    @FormUrlEncoded
    @POST("pos/customersearch")
    Call<SearchCustomer> searchCustomerByMobile(@Field("customer_phone") String customer_phone,
                                                @Field("brand_id") String brand_id,
                                                @Field("customer_add") String customer_add,
                                                @Field("customer_pos_id") String customer_pos_id);

    @FormUrlEncoded
    @POST("pos/customersearch")
    Call<AddCustomerResposne> searchCustomerByMobileAdd(@Field("customer_phone") String customer_phone,
                                                        @Field("brand_id") String brand_id,
                                                        @Field("customer_add") String customer_add,
                                                        @Field("customer_pos_id") String customer_pos_id);

    @GET("pos/customerlist/{restaurant_id}")
    Call<CustomerListData> getCustomerList(@Path("restaurant_id") String restaurant_id);

    @POST("pos/posttemporder")
    Call<TempOrderResponse> postTempOrder(@Body TemporderRequest temporderRequest);

    @POST("pos/saveorder")
    Call<SavedOrderResponse> saveOrder(@Body TemporderRequest temporderRequest);

    @POST("pos/postorder")
    Call<CompleteOrderResponse> postCompleteOrder(@Body CompleteOrderRequest completeOrderRequest);

    @GET("pos/getalltax/{company_id}")
    Call<TaxResponse> getTaxList(@Path("company_id") String company_id);

    @GET("pos/getcountbyordertype/{pos_id}/{device_id}")
    Call<DashboardResponse> getDashBoard(@Path("pos_id") String pos_id,
                                         @Path("device_id") String device_id);

    // get order list
    @GET("pos/getallorderlistbypos/{pos_id}")
    Call<OrderListResponse> getOrderList(@Path("pos_id") String pos_id);

    // get order list
    @GET("pos/order_details/{order_id}")
    Call<OrderDetailsResposne> getOrderDeatils(@Path("order_id") String pos_id);

    @GET("pos/getallsaveorderlistbypos/{pos_id}")
    Call<SavedOrderListRespone> getSavedOrderList(@Path("pos_id") String pos_id);

    @GET("pos/get_saveorder_by_ticket/{ticket_number}")
    Call<OpenTicketResponse> openOrder(@Path("ticket_number") String ticket_number);

    @POST("pos/delete_save_order")
    Call<SavedOrderResponse> deleteSavedOrder(@Body DeletsavedOrderRequest temporderRequest);

    @FormUrlEncoded
    @POST("pos/update_order")
    Call<StatusUpdateResponse> changeOrderStaus(@Field("order_id") String order_id,
                                                @Field("order_status") String order_status);

    @GET("pos/getManagerByResturant/{pos_id}")
    Call<ManagerResponse> getManager(@Path("pos_id") String pos_id);

    @FormUrlEncoded
    @POST("pos/authticate_mager")
    Call<MangerDetails> authenticateManager(@Field("email") String email,
                                            @Field("pass_code") String pass_code);

    @POST("pos/item_refund")
    Call<StatusUpdateResponse> refundItem(@Body RefundItemrequest refundItemrequest);

    @FormUrlEncoded
    @POST("pos/logout")
    Call<StatusUpdateResponse> doLogout(@Field("email") String email,
                                        @Field("device_id") String device_id);


    @FormUrlEncoded
    @POST("pos/logout_other_devices")
    Call<LoginPojo> lastlogoout(@Field("email") String email,
                                @Field("device_id") String device_id);

    @FormUrlEncoded
    @POST("pos/get_brand_point_settings")
    Call<PointSetupData> getPointSetup(@Field("brand_id") String brand_id);

    @FormUrlEncoded
    @POST("pos/update_customer_points")
    Call<StatusUpdateResponse> updateCustomerPoint(@Field("customer_id") String customer_id,
                                                   @Field("type") String type,
                                                   @Field("points") String points,
                                                   @Field("country_id") String country_id,
                                                   @Field("order_number") String order_number,
                                                   @Field("resturant_id") String resturant_id);

    @GET("restaurant/resturant_tables/{restaurant_id}")
    Call<TableData> getTableList(@Path("restaurant_id") String restaurant_id);

    @FormUrlEncoded
    @POST("reservation/table_request_by_resturant")
    Call<MyRequestData> getTableRequest(@Field("resturant_id") String restaurant_id);

    @FormUrlEncoded
    @POST("reservation/request_action")
    Call<ActionResponse> actionRequest(@Field("reservation_id") String reservation_id,
                                       @Field("action") String action,
                                       @Field("resturnat_id") int resturnat_id);

    @FormUrlEncoded
    @POST("reservation/check_table_avalibility")
    Call<ActionResponse> checkTableAvailability(@Field("date_time") String date_time,
                                                @Field("table_id") int table_id);

    @FormUrlEncoded
    @POST("reservation/table_assignment")
    Call<ActionResponse> assignTable(@Field("table_id") String table_id,
                                     @Field("table_token") String table_token,
                                     @Field("reservation_id") String reservation_id,
                                     @Field("assign_pos_id") String assign_pos_id,
                                     @Field("assign_date") String assign_date,
                                     @Field("floor_id") int floor_id,
                                     @Field("date_time") String date_time,
                                     @Field("reservation_date_time") String reservation_date_time,
                                     @Field("res_from") String res_from,
                                     @Field("res_to") String res_to);

    @FormUrlEncoded
    @POST("reservation/complete_reservation")
    Call<ActionResponse> actionMarkComplete(@Field("reservation_id") String date_time);*/

}
