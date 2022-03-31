// package site.metacoding.project_sh.domain.weapon;

// import javax.persistence.Entity;
// import javax.persistence.EntityListeners;
// import javax.persistence.FetchType;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToOne;

// import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import site.metacoding.project_sh.domain.rpgUser.RpgUser;

// @AllArgsConstructor
// @NoArgsConstructor
// @Data
// @EntityListeners(AuditingEntityListener.class)
// @Entity
// public class Weapon {

// private Integer id;

// private Integer html;
// private Integer jsp;
// private Integer java;
// private Integer spring;

// @OneToOne(fetch = FetchType.EAGER)
// @JoinColumn(name = "rpgId")
// private RpgUser rpgUser;
// }
