package com.lsn.board.controller;

import com.lsn.board.dto.CommentDTO;
import com.lsn.board.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {


    private final CommentService commentService;


    // 비동기 방식을 쓰는  detail.jsp에 댓글을 추가하기위해서
    // @ResponseBody 타입으로 리스트를답아 응답데이터를 detail.jsp로보내고
    // 해당값을 받을경우 .ajax success의 commentList 매개변수로 들어가게된다.
    @PostMapping("/save")
    public @ResponseBody List<CommentDTO> save(@ModelAttribute CommentDTO commentDTO){
        System.out.println("commentDtO =" + commentDTO);
        // jsp에서 입력된 값을 통해 데이터베이스에 저장을 한다
        commentService.save(commentDTO);


        // 데이터 베이스에 데이터를 저장한 후, 해당 게시글의 데이터를 가져와 jsp에 반환한다.
        // 게시글의 번호를 가져오기위해 commentDTO의 boardId를 가져온다.
        List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        return commentDTOList;
    }
}
