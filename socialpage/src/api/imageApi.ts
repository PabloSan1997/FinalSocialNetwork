import { propsApi } from "./propsApi";


export class ImageApi{
    async readImages(token:string, username:string|null, page = 0, size = 10):Promise<ShowImages[]>{
        const urlSend = !username?`/images?page=${page}&size=${size}`:`/images/user?username=${username}&page=${page}&size=${size}`;
        const ft = await fetch(`${propsApi.baseUrl+urlSend}`, {
            method:'GET',
            headers:{
                ...propsApi.onlyAuth(token)
            }
        });
        if(!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    async readOneImage(token:string, id:string, page=0, size=5):Promise<ShowOneImage>{
        const ft = await fetch(`${propsApi.baseUrl}/images/${id}?page=${page}&size=${size}`, {
            method:'GET',
            headers:{
                ...propsApi.onlyAuth(token)
            }
        });
        if(!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    async postImages(token:string, data:AddOneImage):Promise<ShowImages>{
        const ft = await fetch(`${propsApi.baseUrl}/images`, {
            method:'POST',
            headers:{
                ...propsApi.jsonAuth(token)
            },
            body:JSON.stringify(data)
        });
        if(!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    async deleteImage(token:string, id:string):Promise<{id:string}>{
        const ft = await fetch(`${propsApi.baseUrl}/images/${id}`, {
            method:'DELETE',
            headers:{
                ...propsApi.onlyAuth(token)
            }
        });
        if(!ft.ok)
            throw await ft.json() as ErroDto;
        return {id};
    }
    async addLikeImage(token:string, id:string):Promise<LikeImageDto>{
        const ft = await fetch(`${propsApi.baseUrl}/images/like/${id}`, {
            method:'GET',
            headers:{
                ...propsApi.onlyAuth(token)
            }
        });
        if(!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    
}