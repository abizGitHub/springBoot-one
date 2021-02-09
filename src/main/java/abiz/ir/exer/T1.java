/*
package abiz.ir.exer;

import service.UnitService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.servlet.http.Cookie;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class T1 {

   @Autowired
   UnitService unitService;

   @Autowired
   private MockMvc mockMvc;


    @Test
    public void t() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(
           //     post("http://devapp01.icico.net.ir/sign-in")
             post("https://postman-echo.com/post")
                        //.header(HttpHeaders.AUTHORIZATION, "Basic " + Base64Utils.encodeToString("db_zare:123456".getBytes()))

        */
/*        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
                                new BasicNameValuePair("username", "db_zare"),
                                new BasicNameValuePair("password", "123456")))))
        *//*
               .header("Host","postman-echo.com")
                       //.header("Host","devapp01.icico.net.ir")
                        .contentType("multipart/form-data")
                        .param("username", "db_zare")
                        .param("password", "123456")
        ).andExpect(status().isOk()).andReturn().getResponse();
        Cookie cookie = response.getCookie("sso_server");
        String contentAsString = mockMvc.perform(
                get("http://devapp01.icico.net.ir/")
                        .cookie(cookie)
        ).andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        System.out.println(contentAsString);

    }




        @Test
    public void t1() throws Exception {
        System.out.println(SecurityContextHolder.getContext().getAuthentication());

        String contentAsString = mockMvc.perform(
                get("http://devapp01.icico.net.ir/oauth/token")
                        .header(HttpHeaders.AUTHORIZATION,                                "Basic " + Base64Utils.encodeToString("db_zare:123456".getBytes()))
        ).andExpect(status().isFound()).andReturn().getResponse().getContentAsString();

        System.out.println(contentAsString);

        ResultActions resultActions = mockMvc.perform(
                post("http://devapp01.icico.net.ir/sign-in")
                        .content("username=db_zare&password=123456")
        )
                .andExpect(status().isFound());


        mockMvc.perform(post("/api/")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON)
                .content(""))
                .andExpect(status().isUnauthorized());
    }


    @Test
    public void t3() throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "sales");
        params.add("username", "db_zare");
        params.add("password", "823");

        ResultActions result
                = mockMvc.perform(get("http://devapp01.icico.net.ir/oauth/token")
                .params(params)
                .header(HttpHeaders.AUTHORIZATION,                                "Basic " + Base64Utils.encodeToString("db_zare:8543".getBytes()))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result.andReturn().getResponse().getContentAsString();
        System.out.println(resultString);
    }

    @Test
    public void t2(){

        */
/*UserDetails userDetails = userDetailsService.loadUserByUsername ("lokesh");
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new GrantedAuthorityImpl("ROLE_INVALID"));
        Authentication authToken = new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword(), authorities);
        SecurityContextHolder.getContext().setAuthentication(authToken);



        Set<EntityType<?>> set = entityManager.getMetamodel().getEntities();
        Locale locale = LocaleContextHolder.getLocale();
        int cnt = 0;
        StringBuffer buffer = new StringBuffer();
        for (EntityType<?> entityType : set) {
            //System.err.println(cnt+++"- "+message);
            String tbl = entityType.getJavaType().getSimpleName();
            String message = messageSource.getMessage("entity." + StringFormatUtil.makeMessageKey(entityType.getJavaType().getSimpleName(), "-"), null, locale);
            if(entityType.getJavaType().getDeclaredAnnotation(Table.class) == null){
                buffer.append("{\n").append("\"code\": ");
                buffer.append("\"R_").append(tbl).append("\",\n");
                buffer.append("\"title\": ").append("\"").append("نمایش ").append(message).append("\"\n},");
                continue;
            }
            tbl = entityType.getJavaType().getDeclaredAnnotation(Table.class).name().toUpperCase().replace("TBL","");
            buffer.append("{\n").append("\"code\": ");
            buffer.append("\"R").append(tbl).append("\",\n");
            buffer.append("\"title\": ").append("\"").append("نمایش ").append(message).append("\"\n},");

            buffer.append("{\n").append("\"code\": ");
            buffer.append("\"C").append(tbl).append("\",\n");
            buffer.append("\"title\": ").append("\"").append("ایجاد ").append(message).append("\"\n},");

            buffer.append("{\n").append("\"code\": ");
            buffer.append("\"D").append(tbl).append("\",\n");
            buffer.append("\"title\": ").append("\"").append("حذف ").append(message).append("\"\n},");

            buffer.append("{\n").append("\"code\": ");
            buffer.append("\"U").append(tbl).append("\",\n");
            buffer.append("\"title\": ").append("\"").append("به روز رسانی ").append(message).append("\"\n},");

            buffer.append("{\n").append("\"code\": ");
            buffer.append("\"A").append(tbl).append("\",\n");
            buffer.append("\"title\": ").append("\"").append("فعالسازی ").append(message).append("\"\n},");

            buffer.append("{\n").append("\"code\": ");
            buffer.append("\"I").append(tbl).append("\",\n");
            buffer.append("\"title\": ").append("\"").append("غیرفعالسازی ").append(message).append("\"\n},");

            buffer.append("{\n").append("\"code\": ");
            buffer.append("\"F").append(tbl).append("\",\n");
            buffer.append("\"title\": ").append("\"").append("تائید نهایی ").append(message).append("\"\n},");

            buffer.append("{\n").append("\"code\": ");
            buffer.append("\"O").append(tbl).append("\",\n");
            buffer.append("\"title\": ").append("\"").append("لغو تائید نهایی ").append(message).append("\"\n},");

        }

        System.out.println(buffer);




        *//*

    }

}
*/
