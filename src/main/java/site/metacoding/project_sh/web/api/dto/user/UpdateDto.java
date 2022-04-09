package site.metacoding.project_sh.web.api.dto.user;

import org.hibernate.id.IntegralDataTypeHolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import site.metacoding.project_sh.domain.user.User;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateDto {
    private Integer coin;

    public User toEntity() {
        User userCoin = new User();
        userCoin.setCoin(this.coin);
        return userCoin;
    }
}
