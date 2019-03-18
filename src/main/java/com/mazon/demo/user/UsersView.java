package com.mazon.demo.user;

import java.util.ArrayList;
import java.util.List;

public class UsersView {

    private List<UserPart> content;

    public UsersView(){
        this.content = new ArrayList<>();
    }

    public List<UserPart> getListaUsersView(){
        return this.content;
    }

    public void setListaUsersView(List<Object[]> listaUsers){

        System.out.println(listaUsers.size());

        if (!listaUsers.isEmpty()) {
            listaUsers.forEach(item -> {
                if (!item[1].equals("") && item[0] != null ) {
                    UserPart userPart = new UserPart();
                    userPart.setId(Long.parseLong(item[0].toString()));
                    userPart.setNome(item[1].toString());
                    this.content.add(userPart);
                }
            });
        }
    }

}
