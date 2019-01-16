package cn.sxl.utils.ldap;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.Control;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;
import java.util.Hashtable;

/**
 * LDAP 连接
 *
 * @author SxL
 * @since 1.2.0
 * Created on 1/16/2019 1:05 PM.
 */
public class LdapUtils {

    public LdapUtils(String url, String baseDn, String root, String password, LdapContext ctx, Control[] controls) {
        this.URL = url;
        this.BASEDN = baseDn;
        this.root = root;
        this.password = password;
        this.ctx = ctx;
        this.controls = controls;
    }

    public LdapContext ctx;
    public final Control[] controls;

    private final String URL;
    private final String BASEDN;
    private final String root;
    private final String password;
    private final String FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

    public void connect() {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, FACTORY);
        env.put(Context.PROVIDER_URL, URL + BASEDN);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");

        env.put(Context.SECURITY_PRINCIPAL, root);
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            ctx = new InitialLdapContext(env, controls);
            System.out.println("连接成功");

        } catch (javax.naming.AuthenticationException e) {
            System.out.println("连接失败：");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("连接出错：");
            e.printStackTrace();
        }

    }

    public void closeContext() {
        if (ctx != null) {
            try {
                ctx.close();
                System.out.println("连接已关闭");
            } catch (NamingException e) {
                e.printStackTrace();
            }

        }
    }

    public String getUserDN(String filterName, String filterValue) {
        StringBuilder userDN = new StringBuilder();
        connect();
        try {
            SearchControls constraints = new SearchControls();
            constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);

            NamingEnumeration<SearchResult> en = ctx.search("", filterName + "=" + filterValue, constraints);

            if (en == null || !en.hasMoreElements()) {
                System.out.println("未找到该用户");
            }
            // maybe more than one element
            while (en != null && en.hasMoreElements()) {
                Object obj = en.nextElement();
                if (obj != null) {
                    SearchResult si = (SearchResult) obj;
                    userDN.append(si.getName());
                    userDN.append(",").append(BASEDN);
                } else {
                    System.out.println((Object) null);
                }
            }
        } catch (Exception e) {
            System.out.println("查找用户时产生异常。");
            e.printStackTrace();
        }

        return userDN.toString();
    }

    public boolean authenticate(String userDN, String password) {
        boolean validate = false;

        try {
            ctx.addToEnvironment(Context.SECURITY_PRINCIPAL, userDN);
            ctx.addToEnvironment(Context.SECURITY_CREDENTIALS, password);
            ctx.reconnect(controls);
            System.out.println(userDN + " 验证通过");
            validate = true;
        } catch (AuthenticationException e) {
            System.out.println(userDN + " 验证失败");
            System.out.println(e.toString());
        } catch (NamingException e) {
            System.out.println(userDN + " 验证失败");
        }
        closeContext();

        return validate;
    }
}
