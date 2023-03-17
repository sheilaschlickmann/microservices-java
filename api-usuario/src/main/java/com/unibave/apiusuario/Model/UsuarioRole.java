package com.unibave.apiusuario.Model;

import com.unibave.apiusuario.Enums.RoleName;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "users_role")
@NamedQueries({
        @NamedQuery(name = "usuarioRole.findAll", query = "SELECT p FROM UsuarioRole p")
})
public class UsuarioRole implements Serializable {

        private static final long serialVersionUID = 1L;

        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        private Long roleId;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false, unique = true)
        private RoleName roleName;


}
