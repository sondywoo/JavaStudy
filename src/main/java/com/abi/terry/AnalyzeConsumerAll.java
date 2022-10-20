package com.abi.terry;

import com.jdbc.util.JdbcUtil1;
import com.jdbc.util.JdbcUtil2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class AnalyzeConsumerAll {
    public static void main(String[] args) {
        AnalyzeConsumerAll o = new AnalyzeConsumerAll();
        o.jdbcConnectDB();
    }

    public void jdbcConnectDB(){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;

        String sql = "SELECT count(1) FROM dbo.customer_all";
        String[] cols = {"campaign_name",
                "city",
                "code",
                "company",
                "content_name",
                "country",
                "county",
                "create_from",
                "create_from_name",
                "create_method",
                "date_created",
                "date_join",
                "date_member_reg",
                "email",
                "email_verified",
                "employee_number",
                "external_id",
                "gender",
                "home_address",
                "id",
                "img",
                "industry",
                "is_anonymous",
                "is_employee",
                "is_member",
                "is_referrer",
                "last_updated",
                "latest_engage_date",
                "membership_level",
                "membership_no",
                "membership_score",
                "mobile_verified",
                "mobilemd5",
                "name",
                "nick_name",
                "not_accept_email",
                "not_acceptsms",
                "office_address",
                "owner_id",
                "owner_name",
                "postcode",
                "province",
                "pt_etl_date",
                "qq",
                "referrer",
                "referrer_name",
                "region",
                "remark",
                "source",
                "stage",
                "status",
                "street",
                "tags",
                "telephone",
                "tenant_id",
                "title",
                "type",
                "type_name",
                "update_method",
                "utm_medium",
                "utm_term",
                "version",
                "website",
                "wechat",
                "wechat_city",
                "wechat_nick_name",
                "weibo"};

        try{
            con = JdbcUtil2.getConnection();
            System.out.println(con);

            if(con == null){
                return;
            }
            stmt = con.createStatement();


            sql = "select sum(1) " +
                    "from dbo.customer_all " +
                    "where <col> is null or <col>='NULL';";
            for(String col : cols){
                System.out.println("\n\n\n=================================\nColumn: " + col + "\n");
                String newsql = sql.replaceAll("<col>", col);
                System.out.println("Executing: " + newsql);
                rs = stmt.executeQuery(newsql);
                System.out.println(col);
                JdbcUtil1.printResultSet(rs);
            }



        }catch(Exception e){
            e.printStackTrace();
        }finally{
            JdbcUtil1.close(rs, stmt, con);
        }
    }
}
