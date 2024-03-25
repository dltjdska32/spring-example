package com.lsn.board.controller;

import com.lsn.board.dto.BoardDTO;
import com.lsn.board.dto.CommentDTO;
import com.lsn.board.dto.PageDTO;
import com.lsn.board.service.BoardService;
import com.lsn.board.service.CommentService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/board")
public class BoardController {

    private final BoardService boardService;

    private final CommentService commentService;

    @GetMapping(value = "/save")
    public String saveForm() {
        return "save";
    }

    //작성요청 처리
    @PostMapping("/save")
    public String save(@ModelAttribute BoardDTO boardDTO) {
        int saveResult = boardService.save(boardDTO);
        // 0보다 클경우 저장성공 , 실패시 save페이지에 머물도록
        if(saveResult > 0) {
            return "redirect:/board/paging";
        } else {
            return "save";
        }
    }

    @GetMapping("/")
    public String findAll(Model model) {
        List<BoardDTO> boardDTOList = boardService.findAll();
        model.addAttribute("boardList", boardDTOList);
        return "list";
    }

    @GetMapping
    //Requestparam -> id라는 파라미터에 담겨온 값을 id라는 long타입에 담겠다.
    public String findById(@RequestParam("id") Long id
                           ,@RequestParam(value = "page", required = false) int page
                            ,Model model){
        // 조회수 갱신을 위한 함수
        boardService.updateHits(id);
        BoardDTO boardDTO = boardService.findById(id);
        //detail.jsp에 baord객체를 넘긴다.
        model.addAttribute("board", boardDTO);
        model.addAttribute("page", page);
        List<CommentDTO> commentDTOList = commentService.findAll(id);
        model.addAttribute("commentList", commentDTOList);
        return "detail";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        boardService.delete(id);
        //삭제가 끝나면 목록을 띄워줌
        return "redirect:/board/";
    }

    //수정하기 전 정보를 수정하는 update페이지
    @GetMapping("/update")
    public String updateForm(@RequestParam("id") Long id, Model model) {
        // 수정할 객체를 가져온다.
        BoardDTO boardDTO = boardService.findById(id);
        //수정할 객체를 담아 update페이지에 뿌려준다.
        model.addAttribute("board", boardDTO);
        return "update";
    }

    // updateForm() 에서 뿌려준 값을 가지고 update페이지에서 form 태그를 통해서 수정한 값을 가지고
    // 실질적으로 수정을 진행하고 반환하는 메소드
    @PostMapping("/update")
    public String update(@ModelAttribute BoardDTO boardDTO, Model model) {
        boardService.update(boardDTO);
        BoardDTO dto = boardService.findById(boardDTO.getId());
        model.addAttribute("board", dto);
        return "detail";
        // 아래의 방법으로 진행할 경우 조회수가 늘어남
        //return "redirect:/board?id=" + boardDTo.getId();
    }

    //페이징 처리를 위한 메소드 -> 사용자가 몇페이지를 볼지를 선택하면 실행
    // 페이징처리가 적용되면서 1페이지 2페이지 3페이지....으로 가면서 게시글의 목록이 달라짐
    //default 페이지는 1페이지
    // ex) /board/paging?page=2
    //RequestParam (value(파라미터값), required(파라미터값 필수or 필수x), defaultvalue(필수가 아니라면 디폴트값을 넣어줌))
    //ex) /paging?page=1
    //만약 RequestParam에 required가 없다면  파라미터값이 필수라는뜻
    @GetMapping("/paging")
    public String paging(Model model
                        ,@RequestParam(value = "page", required = false, defaultValue = "1") int page ) {

        //모든 게시글을 가져온다.
        System.out.println("page = " + page);

        // 해당 페이지에서 보여줄 글 목록 디비에서 가져옴
        List<BoardDTO> pagingList = boardService.pagingList(page);
        System.out.println("pageList = "  + pagingList);
        PageDTO pageDTO = boardService.pagingParam(page);
        model.addAttribute("boardList", pagingList);
        model.addAttribute("paging", pageDTO);

        return "paging";
    }




}
