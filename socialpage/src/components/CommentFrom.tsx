

import React, { FormEvent } from "react";
import { useAppDispatch, useAppSelector } from "../store/hooks";
import { socialExtraReducer } from "../slices/extraReducer/socialExtraReducer";

export default function CommentFrom({ idImage }: { idImage: string }) {
    const [comment, setComment] = React.useState('');
    const dispatch = useAppDispatch();
    const authStated = useAppSelector(state => state.authReducer);
    const submit = (e: FormEvent<HTMLFormElement>) => {
        e.preventDefault();
        if (comment.trim())
            dispatch(socialExtraReducer.commentImage({ token: authStated.token, addComment: { comment, idImage } }))
    }
    return (
        <form className="comment_form" onSubmit={submit}>
            <label htmlFor="">Comentar</label>
            <input
                type="text"
                placeholder="Escribir..."
                onChange={e => setComment(e.target.value)}
                value={comment}
            />
            <button type="submit">Agregar</button>
        </form>
    );
}
