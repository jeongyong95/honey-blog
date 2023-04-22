const Navigation = () => {
  return (
      <nav>
        <ul>
          <li><a href={"/home"}>홈페이지</a></li>
          <li><a href={"/about"}>소개</a></li>
          <li><a href={"/"}>게시글</a></li>
          <li><a href={"/users/register"}>가입하기</a></li>
        </ul>
      </nav>
  )
}

export default Navigation