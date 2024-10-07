/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";
import { useParams } from "react-router-dom";
import { ImagenShow } from "./ImagenShow";


export function ImageContainer() {
    const stateAuth = useAppSelector(state => state.authReducer);
    const stateSocial = useAppSelector(state=>state.socialReducer);
    const dispatch = useAppDispatch();
    const params = useParams();
    const page:number = Number(params.page)? Number(params.page):0;
    console.log(page);
    useEffect(() => {
        if (stateAuth.token.trim())
            dispatch(socialExtraReducer.findAllImages({ token: stateAuth.token , page}));
    }, [stateAuth.token, page])
    return (
        <div className="container">
            {stateSocial.imagenes.map(im => (
                <ImagenShow key={im.id} {...im}/>
            ))}
        </div>
    );
}
