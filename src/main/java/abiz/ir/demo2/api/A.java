package abiz.ir.demo2.api;
import abiz.ir.demo2.model.entity.UserInfo;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Api(tags = {"aaa"})
public interface A {
    @ApiOperation(value = "عنوان یک", nickname = "getAAA", notes = "getAAA", response = UserInfo.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "عُیبسیبد", response = UserInfo.class),
            @ApiResponse(code = 406, message = "08008<br/>طیسیبنشد  ")})
    @GetMapping(value = "/aa/{bbb}",
            produces = {"application/json"})
    ResponseEntity<UserInfo> getAAA(@ApiParam(value = "کد d", required = true) @PathVariable("bbb") String bbb);

}
