package com.wikia.webdriver.testcases.visualeditor.entrypoint;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.wikia.webdriver.common.contentpatterns.PageContent;
import com.wikia.webdriver.common.contentpatterns.URLsContent;
import com.wikia.webdriver.common.properties.Credentials;
import com.wikia.webdriver.common.templates.NewTestTemplateBeforeClass;
import com.wikia.webdriver.pageobjectsfactory.pageobject.WikiBasePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.ArticlePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.SourceEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.article.editmode.VisualEditModePageObject;
import com.wikia.webdriver.pageobjectsfactory.pageobject.visualeditor.VisualEditorPageObject;

/**
 * @author Robert 'Rochan' Chan
 *
 * Editor Entry Point Test on wiki that has wgEnabledRTEExt = true, wgVisualEditorUI = true
 * User Editor Preference is set to VE Editor
 * VE-958 verify VE Editor is loaded when clicking Add Page from the contribution drop down
 * VE-958 verify VE Editor is loaded when clicking the main edit button on the top of the article
 * VE-958 verify VE Editor is loaded when clicking the red link in the article
 * VE-958 verify VE Editor is loaded when clicking the section edit link in the article
 * VE-958 verify VE Editor is loaded when using ?veaction=edit in the URL
 * VE-898 verify VE Editor is loaded on List namespace
 * VE-898 verify CK Editor is loaded on Category namespace
 * VE-898 verify Src Editor is loaded on Template namespace
 * VE-898 verify CK Editor is loaded when using ?action=edit in the URL
 */

public class VEEnabledEditorEntryVEPreferredTests extends NewTestTemplateBeforeClass {

	Credentials credentials = config.getCredentials();
	WikiBasePageObject base;
	String wikiURL;

	@BeforeMethod(alwaysRun = true)
	public void setup_VEPreferred() {
		wikiURL = urlBuilder.getUrlForWiki(URLsContent.VE_ENABLED_WIKI);
		base = new WikiBasePageObject(driver);
		base.logInCookie(credentials.userNameVEPreferred, credentials.passwordVEPreferred, wikiURL);
	}

	@Test(
		groups = {"VEEnabledEditorEntryVEPreferred", "VEEnabledEditorEntryVEPreferredTests_001", "createPageEntry"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_001_CreatePageEntry() {
		String articleName = PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp();
		ArticlePageObject article =
			base.openArticleByName(wikiURL, articleName);
		VisualEditorPageObject ve = article.createArticleInVEUsingDropdown(articleName);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryVEPreferred", "VEEnabledEditorEntryVEPreferredTests_002", "articleEditEntry"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_002_MainEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
		VisualEditorPageObject ve = article.openVEModeWithMainEditButton();
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryVEPreferred", "VEEnabledEditorEntryVEPreferredTests_003", "redlinkEntry"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_003_RedlinkEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		VisualEditorPageObject ve = article.openVEModeWithRedLinks(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryVEPreferred", "VEEnabledEditorEntryVEPreferredTests_004", "sectionEditEntry"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_004_SectionEditEntry() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TESTINGPAGE);
		VisualEditorPageObject ve = article.openVEModeWithSectionEditButton(0);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryVEPreferred", "VEEnabledEditorEntryVEPreferredTests_005", "veactionURLEntry"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_005_URLEntry() {
		VisualEditorPageObject ve = base.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryVEPreferred", "VEEnabledEditorEntryVEPreferredTests_006"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_006_ListNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.LIST_PAGE);
		VisualEditorPageObject ve = article.openNewArticleEditModeVisual(wikiURL);
		ve.verifyVEToolBarPresent();
		ve.verifyEditorSurfacePresent();
	}

	@Test(
		groups = {"VEEnabledEditorEntryVEPreferred", "VEEnabledEditorEntryVEPreferredTests_007"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_007_CategoryNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.CATEGORY_PAGE);
		VisualEditModePageObject ck = article.openCKModeWithMainEditButton();
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}

	@Test(
		groups = {"VEEnabledEditorEntryVEPreferred", "VEEnabledEditorEntryVEPreferredTests_008"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_008_TemplateNamespace() {
		ArticlePageObject article =
			base.openArticleByName(wikiURL, URLsContent.TEMPLATE_PAGE);
		SourceEditModePageObject src = article.openSrcModeWithMainEditButton();
		src.verifySourceOnlyMode();
	}

	@Test(
		groups = {"VEEnabledEditorEntryVEPreferred", "VEEnabledEditorEntryVEPreferredTests_009", "actionURLEntry"}
	)
	public void VEEnabledEditorEntryVEPreferredTests_009_actionEdit() {
		VisualEditModePageObject ck =
			base.navigateToArticleEditPageCK(wikiURL, PageContent.ARTICLE_NAME_PREFIX + base.getTimeStamp());
		ck.verifyContentLoaded();
		ck.clickPublishButton();
	}
}