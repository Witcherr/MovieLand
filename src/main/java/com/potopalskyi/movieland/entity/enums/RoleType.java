package com.potopalskyi.movieland.entity.enums;

public enum RoleType {

    ADMIN(1), USER(0);

    private int role;

    RoleType(int role) {
        this.role = role;
    }

    public static RoleType getRoleById(int role){
        for(RoleType roleType: RoleType.values()){
            if(role == roleType.getRole()){
                return roleType;
            }
        }
        return null;
    }
    public int getRole(){
        return role;
    }

    public boolean equalOrHigher(RoleType requiredRole) {
        return role >= requiredRole.role;
    }
}
