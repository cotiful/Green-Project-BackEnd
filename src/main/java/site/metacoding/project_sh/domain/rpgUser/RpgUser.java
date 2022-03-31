// package site.metacoding.project_sh.domain.rpgUser;

// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.EntityListeners;
// import javax.persistence.FetchType;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
// import javax.persistence.JoinColumn;
// import javax.persistence.OneToOne;

// import org.springframework.data.jpa.domain.support.AuditingEntityListener;

// import lombok.AllArgsConstructor;
// import lombok.Data;
// import lombok.NoArgsConstructor;
// import site.metacoding.project_sh.domain.user.User;

// @AllArgsConstructor
// @NoArgsConstructor
// @Data
// @EntityListeners(AuditingEntityListener.class)
// @Entity
// public class RpgUser {

// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// private Integer id;

// @Column(nullable = false)
// private Integer attack;

// @Column(nullable = false)
// private Integer maxHp;

// @Column(nullable = false)
// private Integer hp;

// @JoinColumn(name = "userId")
// @OneToOne(fetch = FetchType.EAGER)
// private User mainUser;

// }
