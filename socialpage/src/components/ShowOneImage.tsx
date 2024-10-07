/* eslint-disable react-hooks/exhaustive-deps */




export function ShowOneImage(imageInfo: ShowOneImage) {
   
    return (
        <div className="oneImage">
            <img src={imageInfo.urlImage} alt="" />
        </div>
    );
}
