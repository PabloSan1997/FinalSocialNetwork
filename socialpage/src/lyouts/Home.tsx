import { useParams } from "react-router-dom";
import { FormAddImage } from "../components/FormAddImage";
import { HomeNextComponent } from "../components/HomeNextComponent";
import { ImageContainer } from "../components/ImageContainer";


export function Home() {
  document.title = 'Home';
  const params = useParams();

  return (
    <>
      <FormAddImage/>
      <ImageContainer />
      <HomeNextComponent pathbase="/home/" page={Number(params.page)} className="next_home"/>
    </>
  );
}
