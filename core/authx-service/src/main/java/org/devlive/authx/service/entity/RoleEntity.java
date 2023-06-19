package org.devlive.authx.service.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.devlive.authx.common.support.DateSuooprt;
import org.devlive.authx.service.entity.system.menu.SystemMenuModel;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.Date;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@EntityListeners(value = AuditingEntityListener.class)
@Table(name = "system_role")
@JsonIgnoreProperties(value = {
        "menuList"
})
public class RoleEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @Column(name = "active")
    private Boolean active;

    @Column(name = "create_time")
    @CreatedDate
    @DateTimeFormat(pattern = DateSuooprt.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)
    private Date createTime;

    @Column(name = "update_time")
    @LastModifiedDate
    @DateTimeFormat(pattern = DateSuooprt.DATE_FORMAT_YYYY_MM_DD_HH_MM_SS)
    private Date updateTime;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "system_role_menu_relation",
            joinColumns = @JoinColumn(name = "system_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "system_menu_id", referencedColumnName = "id"))
    private List<SystemMenuModel> menuList;

}
