import { FormAddImage } from "../components/FormAddImage";
import { ImageContainer } from "../components/ImageContainer";


export function Home() {
  document.title = 'Home';
  return (
    <>
      <FormAddImage/>
      <ImageContainer />
    </>
  );
}
