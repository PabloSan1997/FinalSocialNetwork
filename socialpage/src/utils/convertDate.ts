


export const converDate = (data:string):string =>{
    const newDate = new Date(data);
    const fecha = newDate.toLocaleDateString();
    const hora = newDate.toLocaleTimeString();
    return `${fecha} ${hora}`;
}