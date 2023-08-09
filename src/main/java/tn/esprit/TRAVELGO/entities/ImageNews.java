package tn.esprit.TRAVELGO.entities;



import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImageNews implements MultipartFile {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idImageNews ;
	private String nameImageNews;
	@Lob
	private byte[] data;
	@ManyToOne
	News news;

	public ImageNews(String nameImageNews, String contenttype, byte[] data) {
		super();
		this.nameImageNews = nameImageNews;
		this.getContentType();
		this.data = data;
	}


	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getOriginalFilename() {
		return null;
	}

	@Override
	public String getContentType() {
		return null;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

	@Override
	public long getSize() {
		return 0;
	}

	@Override
	public byte[] getBytes() throws IOException {
		return new byte[0];
	}
	@Override
	public InputStream getInputStream() throws IOException {
		return null;
	}
	@Override
	public void transferTo(File dest) throws IOException, IllegalStateException {

	}
}
