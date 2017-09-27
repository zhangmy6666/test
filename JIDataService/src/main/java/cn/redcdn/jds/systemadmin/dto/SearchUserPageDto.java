package cn.redcdn.jds.systemadmin.dto;

import cn.redcdn.jds.common.dto.PageInfoDto;

public class SearchUserPageDto extends PageInfoDto
{
	 /**
     * 搜索类型
     */
    private String searchType;
    
    /**
     * 搜索内容
     */
    private String content;

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
