import { FormEvent, useState } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import '../styles/addImage.scss';

const entryValues:AddOneImage = {
    urlImage: "",
    description: ""
}

export  function FormAddImage() {
    const [data, setData] = useState<AddOneImage>(entryValues);
    const disptach = useAppDispatch();
    const token = useAppSelector(state => state.authReducer.token);
    const submit = (e:FormEvent<HTMLFormElement>) =>{
        e.preventDefault();
        if(data.description.trim() && data.urlImage.trim()){
            disptach(socialExtraReducer.saveImage({token, newImage:data})).then(()=>{
                setData(entryValues);
            });
        }
    }
  return (
    <form className="add_form" onSubmit={submit}>
        <textarea 
        placeholder="Comparte algo nuevo"
        onChange={e => setData({...data, description:e.target.value})}
        value={data.description}
        rows={3}
        ></textarea>
        <input 
        type="text" 
        placeholder="Url Imagen"
        onChange={e => setData({...data, urlImage:e.target.value})}
        value={data.urlImage}
        />
        <button type="submit">Publicar</button>
    </form>
  );
}
