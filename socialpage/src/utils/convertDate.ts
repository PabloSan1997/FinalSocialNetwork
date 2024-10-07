


export const converDate = (data:string):string =>{
    const fecha = new Date(data);
    return fecha.toLocaleString();
}