package org.jboss.spring3_2.example.MatrixVariables.repo;

import java.util.List;

import org.jboss.spring3_2.example.MatrixVariables.domain.Member;

public interface MemberDao
{
    public Member findById(Long id);

    public List<Member> findByNameAndEmail(String name, String email);

    public Member findByEmail(String email);

    public List<Member> findAllOrderedByName();

    public void register(Member member);
}
