package com.lsn.board.service;

import com.lsn.board.dto.BoardDTO;
import com.lsn.board.dto.PageDTO;
import com.lsn.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Indexed;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class BoardService {

    //페이지당 개시글 수
    private int pageLimit = 3;

    // 하단에 보여줄 페이지 번호 갯수
    private int blockLimit = 3;

    private final BoardRepository boardRepository;
    public int save(BoardDTO boardDTO) {
        return boardRepository.save(boardDTO);
    }
    public List<BoardDTO> findAll() {
        return boardRepository.findAll();
    }

    public BoardDTO findById(Long id) {
        return boardRepository.findById(id);
    }

    public void updateHits(Long id) {
        boardRepository.updateHits(id);
    }

    public void delete(Long id) {
        boardRepository.delete(id);
    }

    public void update(BoardDTO boardDTO) {
        boardRepository.update(boardDTO);
    }

    public List<BoardDTO> pagingList(int page) {


        /*
        * 1 페이지당 보여지는 글 갯수 3개로 제한
        *  1PAGE = > 0
        *  2PAGE = > 3
        *  3PAGE = > 6
        * */

        int pagingStart = (page -1) * pageLimit;

        // 페이징 처리를 위해 mysql에서 아래의 쿼리를 날림
        // ex) select * from board_table order by id desc limit 13,3;
        //      -> 이쿼리는 보드테이블(게시글) limit을 통해 id (13 ) 게시글 부터 3개씩 출력하는뜻
        // 이 쿼리에서 lmit start, Limit 임 따라서 맵을 통해서 Limit과 start를 저장해둔다.
        // 해당 해쉬맵에 값을 담아 db에 보내 쿼리를 작성하기 위해서
        Map<String, Integer> pagingParams = new HashMap<>();
        pagingParams.put("start", pagingStart);
        pagingParams.put("limit", pageLimit);
        List<BoardDTO> pagingList = boardRepository.pagingList(pagingParams);

        return pagingList;
    }

    public PageDTO pagingParam(int page) {

        // 전체 글 갯수 조회
        int boardCount = boardRepository.boardCount();

        //전체 페이지 갯수 계산
        // ex) 10 (전체글) / 3 (하단 페이지 번호 갯수) = 3.3333.... 그렇기 때문에
        // ceil함수를 사용해서 올림처리를 하여 4로 만듬 즉, 한단 페이지 번호 갯수는 4까지 보여줌
        int maxPage = (int) (Math.ceil((double) boardCount / pageLimit));

        // 시작 페이지 값 계산 ( 1, 4, 7 ,10 , ~~~)
        int startPage = (((int) (Math.ceil((double) page / blockLimit))) - 1) * blockLimit + 1;

        // 끝 페이지 값 계산( 3, 6 ,9 ,12 ~~~~~)
        int endPage = startPage + blockLimit -1;

        if (endPage > maxPage) {
            endPage = maxPage;
        }

        PageDTO pageDTO = new PageDTO();
        pageDTO.setPage(page);
        pageDTO.setMaxPage(maxPage);
        pageDTO.setEndPage(endPage);
        pageDTO.setStartPage(startPage);

        return pageDTO;
    }
}
