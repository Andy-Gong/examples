package sharding.jdbc.example.resource.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class UserVO {

    private Integer tenantId;
    private Long userId;
    private BigInteger companyId;
    private String userName;
    private String account;
    private String password;

    public UserVO() {
    }
}
