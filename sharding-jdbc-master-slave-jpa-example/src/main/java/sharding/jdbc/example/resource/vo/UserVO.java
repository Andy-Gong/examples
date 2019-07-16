package sharding.jdbc.example.resource.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
public class UserVO {

    private Integer tenantId;
    private Long userId;
    private BigInteger companyId;
    private String userName;
    private String account;
    private String password;
}
