import {UserSo} from "../../openapi";

const UserItem = (props: UserSo) => {
    return(
        <tr>
            <th scope="row">{props.id}</th>
            <td>{props.name}</td>
            <td>{props.surname}</td>
        </tr>
    )
}

export default UserItem;