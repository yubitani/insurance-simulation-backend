package jp.co.slcs.insurancepremiumsimulation.backend.spring.repository.dao;

import java.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author horikawakeisuke
 * お客様テーブルのDAOEntity
 */
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@RequiredArgsConstructor(onConstructor = @__({ @PersistenceConstructor }))
@Builder
@Getter
@ToString
@Table("CUSTOMER")
public class CustomerDaoEntity implements Persistable<Integer>{

	@Id
	private Integer customerId;
	@NonNull
	private String name;
	@NonNull
	private String kana;
	@NonNull
	private LocalDate birthday;
	@NonNull
	private String sex; // 1:男 2:女
	@NonNull
	private String zipcode;
	@NonNull
	private String address;
	@NonNull
	private String telNo;
	@NonNull
	private String mail;
	@Override
	public Integer getId() {
		return customerId;
	}
	@Builder.Default
    @Setter
    @Transient
    private boolean isNew = false;

}
