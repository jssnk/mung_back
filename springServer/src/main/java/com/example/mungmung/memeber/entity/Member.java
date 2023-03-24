package com.example.mungmung.memeber.entity;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;

@Entity
@NoArgsConstructor
@Setter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mmeber_ id")
    private Long id;

    @Column
    private String email;

    @Column
    private String nickName;

    @Column
    private MemberType memberType;

    @OneToOne(fetch =  FetchType.LAZY, orphanRemoval = true)
    private MemberProfile memberProfile;

    @OneToMany(mappedBy = "member", fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Authentication> authentications = new HashSet<>();


    public Member(String email,String nickName, MemberType memberType, MemberProfile memberProfile ){
        this.email = email;
        this.nickName = nickName;
        this.memberType = memberType;
        this.memberProfile =memberProfile;
    }

    private Optional<Authentication> findBasicAuthentication() {
        return authentications
                .stream()
                .filter(auth -> auth instanceof BasicAuthentication)
                .findFirst();
    }
}