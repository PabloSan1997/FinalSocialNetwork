

export const socialStorage = {
    save(token:string){
        localStorage.xdxd=token;
    },
    read():string{
        if(!localStorage.xdxd){
            localStorage.xdxd = '';
        }
        return localStorage.xdxd;
    }
}