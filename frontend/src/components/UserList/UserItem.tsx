import {UserSo} from "../../openapi";
import useApiHook from "../../hooks/useApiHook";
import {ListGroup} from "react-bootstrap";


type Props = {
    user: UserSo;
    handleUpdate: () => void;
};

const UserItem = (props: Props) => {

    const api = useApiHook();
    const deleteUser = () => {
        api.deleteUser(props.user.id).then(() => {
            props.handleUpdate();
        });
    }

    let url: string = "users/"+props.user.id;

    return(
        <div className="text-center justify-content-center d-flex m-2">
            <ListGroup.Item className="w-50" active action href={url}>
                {props.user.name} {props.user.surname}
            </ListGroup.Item>
        </div>


    )
}

export default UserItem;