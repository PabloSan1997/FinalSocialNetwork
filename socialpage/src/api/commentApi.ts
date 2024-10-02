import { propsApi } from "./propsApi";


export class CommentApi{
    async saveComment(token:string,comment:AddComent):Promise<Comment>{
        const ft = await fetch(`${propsApi.baseUrl}/comments`, {
            method:'POST',
            headers:{
                ...propsApi.jsonAuth(token)
            },
            body:JSON.stringify(comment)
        });
        if(!ft.ok)
            throw await ft.json() as ErroDto;
        return ft.json();
    }
    async deleteComment(token:string, id:number):Promise<{id:number}>{
        const ft = await fetch(`${propsApi.baseUrl}/comments/${id}`, {
            method:'DELETE',
            headers:{
                ...propsApi.onlyAuth(token)
            }
        });
        if(!ft.ok)
            throw await ft.json() as ErroDto;
        return {id};
    } 
}