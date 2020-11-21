package projects.portfoliodemo.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
@EqualsAndHashCode(of = "username")
@ToString(exclude = "password")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;
    private Boolean active =Boolean.FALSE;

    @ElementCollection(fetch = FetchType.EAGER) //mapowana do osobnej tabelki
    @CollectionTable(name = "users_roles", joinColumns =
            @JoinColumn(name = "username", referencedColumnName = "username"),
            indexes =
            @Index(name = "users_roles_username_idx", columnList = "username")) //dopracowanie tabelki
    @Column(name = "role")
    private Set<String> roles;
}
